package com.moonsworkshop.vexcty.backup;

import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import com.moonsworkshop.vexcty.Vexcty;

import java.time.ZonedDateTime;
import java.util.Locale;

public class CronUtil {

    static com.cronutils.model.Cron cron;
    static ExecutionTime executionTime;
    static ZonedDateTime nextExecution;

    public static void checkCron() {
        CronDefinition cronDefinition = CronDefinitionBuilder.defineCron()
                .withSeconds().and()
                .withMinutes().and()
                .withHours().and()
                .withDayOfMonth()
                .supportsHash().supportsL().supportsW().and()
                .withMonth().and()
                .withDayOfWeek()
                .withIntMapping(7, 0) // non-standard non-zero numbers
                .supportsHash().supportsL().supportsW().and()
                .instance();

        CronParser parser = new CronParser(cronDefinition);
        cron = parser.parse(Vexcty.getPlugin().getCrontask());
        cron.validate();

        Vexcty.getPlugin().getLogger().info("Configured the cron task to be: " + CronDescriptor.instance(Locale.US).describe(cron));

        executionTime = ExecutionTime.forCron(cron);
        nextExecution = executionTime.nextExecution(ZonedDateTime.now()).get();
    }

    public static boolean run() {
        ZonedDateTime time = ZonedDateTime.now();
        if (nextExecution.isBefore(time)) {
            nextExecution = executionTime.nextExecution(time).get();
            return true;
        } else {
            return false;
        }
    }
}
