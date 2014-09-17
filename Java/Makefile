JAVAC=javac
JAVA=java

SOURCES+=$(wildcard data_structures/*.java)
SOURCES+=$(wildcard sorting/*.java)
SOURCES+=$(wildcard graphs/*.java)
CLASSES=$(SOURCES:.java=.class)
JUNITPATH=junit-4.11.jar
HAMCRESTPATH=hamcrest-core-1.3.jar
CLASSPATH=.:$(JUNITPATH):$(HAMCRESTPATH)
OPTIONS=-Xlint:unchecked

all: $(CLASSES)

clean:
	-rm data_structures/*.class sorting/*.class graphs/*.class

runsort: all
	$(JAVA) -cp $(CLASSPATH) org.junit.runner.JUnitCore sorting.UnitTester

runstruct: all
	$(JAVA) -cp $(CLASSPATH) org.junit.runner.JUnitCore data_structures.UnitTester

rungraphs: all
	$(JAVA) -cp $(CLASSPATH) org.junit.runner.JUnitCore graphs.UnitTester

%.class: %.java
	$(JAVAC) -cp $(CLASSPATH) $(OPTIONS) $<

.PHONY: all

