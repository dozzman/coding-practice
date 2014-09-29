JAVAC=javac
JAVA=java

DATA_SOURCES=$(wildcard data_structures/*.java)
SORTING_SOURCES=$(wildcard sorting/*.java)
GRAPH_SOURCES=$(wildcard graphs/*.java)
QUESTION_SOURCES=$(wildcard questions/*/*/*.java)
EULER_SOURCES=$(wildcard euler/*/*.java)

DATA_CLASSES=$(DATA_SOURCES:.java=.class)
SORTING_CLASSES=$(SORTING_SOURCES:.java=.class)
GRAPH_CLASSES=$(GRAPH_SOURCES:.java=.class)
QUESTION_CLASSES=$(QUESTION_SOURCES:.java=.class)
EULER_CLASSES=$(EULER_SOURCES:.java=.class)

JUNITPATH=junit-4.11.jar
HAMCRESTPATH=hamcrest-core-1.3.jar
CLASSPATH=.:$(JUNITPATH):$(HAMCRESTPATH)
JAVACOPTIONS=-Xlint:unchecked

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
	-$(RM) data_structures/*.class sorting/*.class graphs/*.class questions/*/*/*.class euler/*/*.class

%.class: %.java
	$(JAVAC) -cp $(CLASSPATH) $(JAVACOPTIONS) $<

.PHONY: all sorting data_structures questions euler runsort runstruct rungraphs clean

