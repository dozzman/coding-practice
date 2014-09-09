JAVAC=javac
JAVA=java

SOURCES+=$(wildcard data_structures/*.java)
SOURCES+=$(wildcard sorting/*.java)
CLASSES=$(SOURCES:.java=.class)

all: $(CLASSES)

clean:
	-rm $(CLASSES) 

runsort: sorting
	$(JAVA) sorting/SortTester

%.class: %.java
	$(JAVAC) $<

.PHONY: all sorting
