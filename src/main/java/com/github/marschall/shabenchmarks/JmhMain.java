package com.github.marschall.shabenchmarks;

import static org.openjdk.jmh.results.format.ResultFormatType.TEXT;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class JmhMain {

  public static void main(String[] args) throws RunnerException {
    runWithRhreads(0);
    runWithRhreads(Runtime.getRuntime().availableProcessors());
  }

  private static void runWithRhreads(int threads) throws RunnerException {
    Options options = new OptionsBuilder()
            .include(JmhMain.class.getPackage().getName() + ".*")
            .jvmArgs("-Xmx4g")
            .warmupIterations(5)
            .warmupTime(TimeValue.seconds(1L))
            .measurementIterations(5)
            .measurementTime(TimeValue.seconds(1L))
            .forks(3)
            .resultFormat(TEXT)
            .threads(threads)
            .output("results-threads-" + threads + ".txt")
            .build();
    new Runner(options).run();
  }

}
