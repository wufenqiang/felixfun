package com.weather.bigdata.it.utils.hdfsUtil;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

public interface MyPathFilterInterface extends PathFilter {
    boolean accept(Path path);
}
