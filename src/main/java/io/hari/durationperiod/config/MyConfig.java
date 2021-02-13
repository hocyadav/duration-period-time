package io.hari.durationperiod.config;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author HariomYadav
 * @since 12/02/21
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Component
@ConfigurationProperties (prefix = "config")
@Validated
public class MyConfig {
    Map<String, String> appConfig;

    Map<String, Integer> timeConfig;

    Map<String, Duration> timeConfigDuration;

    Map<String, DurationClass> timeConfigDurationAdvance;

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @Validated
    public static class DurationClass {
        Duration time1;//recommended way, in config we are setting long value + time unit

        @DurationUnit (ChronoUnit.HOURS)//in config we are only setting long value + here we are setting time unit
        Duration time2;

        Duration time3; // store as milli sec
    }
}
