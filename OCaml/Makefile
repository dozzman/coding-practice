OCAMLOPT=ocamlopt.opt
OCAMLC=ocamlc.opt
FIND=$(shell which find)
CMXFLAGS+=-I data_structures -I sorting

STRUCT_SRCS=$(wildcard data_structures/*.ml)
STRUCT_ITFS=$(wildcard data_structures/*.mli)
STRUCT_ITF_OBJS=$(STRUCT_ITFS:.mli=.cmi)
STRUCT_OBJS=$(STRUCT_SRCS:.ml=.cmx)

SORT_SRCS=$(wildcard sorting/*.ml)
SORT_ITFS=$(wildcard sorting/*.mli)
SORT_ITF_OBJS=$(SORT_ITFS:.mli=.cmi)
SORT_OBJS=$(SORT_SRCS:.ml=.cmx)

EULER_SRCS=$(wildcard euler/*.ml)
EULER_ITFS=$(wildcard euler/*.mli)
EULER_ITF_OBJS=$(EULER_ITFS:.mli=.cmi)
EULER_OBJS=$(EULER_SRCS:.ml=.cmx)

OUTPUTS=SortTest

all: data_structures sorting euler

euler: $(EULER_ITF_OBJS) $(EULER_OBJS)

data_structures: $(STRUCT_ITF_OBJS) $(STRUCT_OBJS)

sorting: $(SORT_ITF_OBJS) SortTest

SortTest: $(SORT_OBJS)
	$(OCAMLOPT) -o $@ $+

%.cmx: %.ml
	$(OCAMLOPT) -o $@ -c $(CMXFLAGS) $<

%.cmi: %.mli
	$(OCAMLOPT) -o $@ -c $(CMIFLAGS) $<

%.cmo: %.ml
	$(OCAMLC) -o $@ -c $(CMOFLAGS) $<
clean:
	$(FIND) . -iname '*.cmo' -exec $(RM) {} + 
	$(FIND) . -iname '*.cmi' -exec $(RM) {} + 
	$(FIND) . -iname '*.cmx' -exec $(RM) {} + 
	$(FIND) . -iname '*.o' -exec $(RM) {} + 
	-$(RM) $(OUTPUTS)

.PHONY: all clean data_structures sorting euler
