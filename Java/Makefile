JAVAC=javac
JAVA=java
FIND=$(shell which find)

DATA_SOURCES=$(wildcard data_structures/*.java)
DATA_CLASSES=$(DATA_SOURCES:.java=.class)

SORTING_SOURCES=$(wildcard sorting/*.java)
SORTING_CLASSES=$(SORTING_SOURCES:.java=.class)

GRAPH_SOURCES=$(wildcard graphs/*.java)
GRAPH_CLASSES=$(GRAPH_SOURCES:.java=.class)

QUESTION_SOURCES=$(wildcard questions/*/*/*.java)
QUESTION_CLASSES=$(QUESTION_SOURCES:.java=.class)

EULER_SOURCES=$(wildcard euler/*/*.java)
EULER_CLASSES=$(EULER_SOURCES:.java=.class)

JUNITPATH=junit-4.11.jar
HAMCRESTPATH=hamcrest-core-1.3.jar
CLASSPATH=.:$(JUNITPATH):$(HAMCRESTPATH)
JAVACFLAGS=-Xlint:unchecked

all: sorting data_structures graphs questions euler

sorting: $(SORTING_CLASSES)

data_structures: $(DATA_CLASSES)

questions: $(QUESTION_CLASSES)

graphs: $(GRAPHS_CLASSES)

euler: $(EULER_CLASSES)

runsort: sorting
	$(JAVA) -cp $(CLASSPATH) org.junit.runner.JUnitCore sorting.UnitTester

runstruct: data_structures
	$(JAVA) -cp $(CLASSPATH) org.junit.runner.JUnitCore data_structures.UnitTester

rungraphs: graphs
	$(JAVA) -cp $(CLASSPATH) org.junit.runner.JUnitCore graphs.UnitTester

clean:
	$(FIND) . -iname '*.class' -exec $(RM) {} +

%.class: %.java
	$(JAVAC) -cp $(CLASSPATH) $(JAVACFLAGS) $<

.PHONY: all sorting data_structures questions euler runsort runstruct rungraphs clean

