package domain.objetos.sensorTemp;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class ConfigCronoTemp {
    public static void ejecutarCronoTemp(){
        try {
            // Crear una instancia del scheduler
            StdSchedulerFactory factory = new StdSchedulerFactory();
            org.quartz.Scheduler scheduler = factory.getScheduler();

            // Crear un JobDetail y agregar los parámetros
            JobDetail job = JobBuilder.newJob(SensorTemperatura.class)
                    .withIdentity("myJobLecturaTemperatura", "group1")
                    .build();

            // Configurar el trigger para ejecutar el trabajo cada 5 minutos
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("myTriggerLecturaTemperatura", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(10)
                            .repeatForever())
                    .build();

            // Asociar el trabajo con el trigger en el scheduler
            scheduler.scheduleJob(job, trigger);

            // Iniciar el scheduler
            scheduler.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
