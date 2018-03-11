package com.wiwj.maigcer.log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * @Author: Joe liuzhaojava@foxmail.com
 * @Date: 2018/3/9 23:39
 * @Description:
 */
public class LogManager {

    public static void init(final boolean isDebug) {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  //（可选）是否显示线程信息。 默认值为true
                .methodCount(2)         // （可选）要显示的方法行数。 默认2
                .methodOffset(5)        // （可选）隐藏内部方法调用到偏移量。 默认5
//                .logStrategy(customlog) //（可选）更改要打印的日志策略。 默认LogCat
                .tag("POWER")   //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return isDebug;
            }
        });
//        FormatStrategy csvStrategy = CsvFormatStrategy.newBuilder()
//                .tag("custom").build();//保存指定的TAG
//        Logger.addLogAdapter(new DiskLogAdapter(csvStrategy));//将日志保存在文件中
    }
}

