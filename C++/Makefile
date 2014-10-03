SORTING_SOURCES=$(wildcard sorting/*.cc)
SORTING_OBJECTS=$(SORTING_SOURCES:.cc=.o)
SORTING_DEPS=$(SORTING_OBJECTS:.o=.d)

GTEST_DIR=gtest-1.7.0

CXXFLAGS+=-Wall -std=c++11 -isystem $(GTEST_DIR)/include

OUTPUTS=SortTest

all: sorting euler

-include $(SORTING_DEPS)

sorting: SortTest

SortTest: libgtest.a $(SORTING_OBJECTS)
	$(CXX) -o SortTest $(LDFLAGS) $+

euler: 
	$(MAKE) -C euler/problem9

libgtest.a: gtest-all.o
	ar -r libgtest.a gtest-all.o

gtest-all.o: $(GTEST_DIR)/src/gtest-all.cc
	$(CXX) -isystem $(GTEST_DIR)/include -I$(GTEST_DIR) -pthread -c $<

%.o: %.cc
	$(CXX) -c $(CXXFLAGS) -o $@ $<
	$(CXX) -MM -MT $@ $(CXXFLAGS) -o $*.d $<

clean:
	$(FIND) . -iname '*.[od]' -exec $(RM) {} +
	-$(RM) $(OUTPUTS)


.PHONY: all sorting clean euler
