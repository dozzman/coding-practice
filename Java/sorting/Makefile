JAVAC=javac
java=java

all: Sorter.class sorters SortTester.class

sorters: BubbleSort.class

clean:
	rm *.class

run: all
	java SortTester.class

%.class: %.java
	javac -cp . $<

.PHONY: all sorters clean run
