package domain.realizadorDeReporte.CronoInforme;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class ConfigCronoInforme {
    public static void ejecutarCronoInforme(String rutaArchivo){
        try {
            // Crear una instancia del scheduler
            StdSchedulerFactory factory = new StdSchedulerFactory();
            org.quartz.Scheduler scheduler = factory.getScheduler();

            // Crear un JobDetail y agregar los parámetros
            JobDetail job = JobBuilder.newJob(JobInforme.class)
                    .withIdentity("myJobInforme", "group1")
                    .usingJobData("ruta", rutaArchivo)
                    .build();

            // Configurar el trigger para ejecutar el trabajo cada 5 minutos
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInMinutes(5)
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
