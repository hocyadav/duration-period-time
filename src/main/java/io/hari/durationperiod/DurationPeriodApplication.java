package io.hari.durationperiod;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.hari.durationperiod.config.MyConfig;

@SpringBootApplication
public class DurationPeriodApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(DurationPeriodApplication.class, args);
    }

    @Autowired
    MyConfig myConfig;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("DurationPeriodApplication.run");
        testDuration_n_Period_API();

        System.out.println("random = " + RandomStringUtils.randomAlphabetic(5));
        System.out.println("random = " + RandomStringUtils.randomAlphabetic(5));

        System.out.println("abcde = " + RandomStringUtils.random(4, "abcdefgh"));
        System.out.println("abcde = " + RandomStringUtils.random(4, "abcdefgh"));
        System.out.println("abcde = " + RandomStringUtils.random(4, "abcdefgh"));
        System.out.println("abcde = " + RandomStringUtils.random(5, String.valueOf(System.currentTimeMillis())));
        System.out.println("abcde = " + RandomStringUtils.random(7, UUID.randomUUID().toString()));
        System.out.println("abcde = " + RandomStringUtils.random(7, UUID.randomUUID().toString().concat("abcdef")));
        System.out.println("abcde = " + RandomStringUtils.random(7, UUID.randomUUID().toString().concat("abcdef")));
        Random random = new Random();
        final int nextInt = random.nextInt(2);
        System.out.println("nextInt = " + nextInt);

        System.out.println(getString());
        System.out.println(getString());
        System.out.println(getString());
        System.out.println(getString());
        System.out.println(getString());
        System.out.println(getString());
        System.out.println(getString());
        System.out.println(getString());

        /** db to java time: Timestamp API**/
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("timestamp = " + timestamp.toString());
        long time = timestamp.getTime();//
        System.out.println("time = " + new Timestamp(time));
        time = time + 1 * 1000;
        System.out.println("time = " + time);
        System.out.println("new Timestamp(time) = " + new Timestamp(time));

        /** Timestamp to localdate time**/
        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        System.out.println("timestamp1 = " + timestamp1);
        final LocalDateTime localDateTime = timestamp1.toLocalDateTime();
        System.out.println("localDateTime = " + localDateTime);
        /** extract date from local date**/
        final LocalDate localDate = timestamp1.toLocalDateTime().toLocalDate();
        System.out.println("localDate = " + localDate);
        /** extract time from local date**/
        final LocalTime localTime = timestamp1.toLocalDateTime().toLocalTime();
        System.out.println("localTime = " + localTime);

        /** remove milisecond from local date time or local time**/
        final LocalDateTime localDateTime1 = timestamp1.toLocalDateTime().withNano(0);
        System.out.println("localDateTime1 = " + localDateTime1);

        final LocalTime localTime1 = timestamp1.toLocalDateTime().toLocalTime().withNano(0);
        System.out.println("localTime1 = " + localTime1);
        final LocalDateTime localDateTime2 = timestamp1.toLocalDateTime().withNano(0);//remove mili
        System.out.println("localDateTime2 = " + localDateTime2);
        final LocalTime localTime2 = timestamp1.toLocalDateTime().toLocalTime().withSecond(0).withNano(0);//remove sec + remove mili
        System.out.println("localTime2 = " + localTime2);

        //todo : Date, DateFormat , LocalDate, TimeStamp conversion
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//        LocalDateTime localDateTime3 = LocalDateTime.now();
//        final Date parse = simpleDateFormat.parse("26/06/1989 18:11:03");
//        System.out.println("parse = " + parse);
//        final long time1 = parse.getTime();
//        final Timestamp timestamp2 = new Timestamp(time1);
//        System.out.println("timestamp2 = " + timestamp2);
    }

    private String getString() {
        Random random = new Random();
        final String stringPart = RandomStringUtils.random(4, "ABCDEFGHIJKLMNOPQRSTUVWZYZ123456789".toUpperCase());
        final int intPart = random.ints(100, 999).findFirst().getAsInt();
        final String finalUniqueId = intPart + "-" + stringPart;
        return finalUniqueId;
    }

    private void testDuration_n_Period_API() {
        System.out.println("appConfig = " + myConfig.getAppConfig());
        System.out.println("timeConfig = " + myConfig.getTimeConfig());
        final Map<String, Duration> timeConfigDuration = myConfig.getTimeConfigDuration();
        timeConfigDuration.forEach((s, duration) -> {
            final long seconds = duration.getSeconds();
            System.out.println("seconds = " + seconds);
        });
        final List<Long> second = timeConfigDuration.entrySet().stream().map(stringDurationEntry -> {
            final Map.Entry<String, Duration> stringDurationEntry1 = stringDurationEntry;
            final Duration value = stringDurationEntry.getValue();
            return value.getSeconds();
        }).collect(Collectors.toList());
        System.out.println("second = " + second);

        System.out.println("getTimeConfigDuration = " + timeConfigDuration);

        final Map<String, MyConfig.DurationClass> timeConfigDurationAdvance = myConfig.getTimeConfigDurationAdvance();
        timeConfigDurationAdvance.forEach((s, durationClass) -> {
            final MyConfig.DurationClass durationClass1 = durationClass;
            final Duration time1 = durationClass.getTime1();
            final Duration time2 = durationClass.getTime2();
            System.out.println("time1 = " + time1.getSeconds());
            System.out.println("time2 = " + time2.toMinutes());
        });
        System.out.println("timeConfigDurationAdvance = " + timeConfigDurationAdvance);
        System.out.println("timeConfigDurationAdvance.get(\"timeValue\") = " + timeConfigDurationAdvance.get("timeValue"));
        System.out.println("timeConfigDurationAdvance.get(\"timeValue\") time 1 = " + timeConfigDurationAdvance.get("timeValue").getTime1().getSeconds());
        System.out.println("timeConfigDurationAdvance.get(\"timeValue\") time 2= " + timeConfigDurationAdvance.get("timeValue").getTime2().toMinutes());
        System.out.println("timeConfigDurationAdvance.get(\"timeValue\") time 3= " + timeConfigDurationAdvance.get("timeValue").getTime3().toMillis());
    }
}
