#!/bin/bash
mv build/native/nativeCompile/bml-java ./bml-java
chmod +x bml-java

./bml-java config.json > /dev/null
cat benchmark.json