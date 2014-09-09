OCAMLOPT=ocamlopt.opt
OCAMLC=ocamlc.opt
CMIOPTIONS=-c
CMOOPTIONS=-c
CMXOPTIONS=-c

INTERFACES=$(wildcard *.mli)
INTERFACE_OBJECTS=$(INTERFACES:.mli=.cmi)
SOURCES=$(wildcard *.ml)
OBJECTS=$(SOURCES:.ml=.cmx)
OUTPUTS=SortTest

all: $(INTERFACE_OBJECTS) $(OBJECTS) SortTest

SortTest: BubbleSort.cmx BSTree.cmx SortTest.cmx
	$(OCAMLOPT) -o $@ $+

%.cmx: %.ml
	$(OCAMLOPT) -o $@ $(CMXOPTIONS) $<

%.cmi: %.mli
	$(OCAMLOPT) -o $@ $(CMIOPTIONS) $<

%.cmo: %.ml
	$(OCAMLC) -o $@ $(CMOOPTIONS) $<
clean:
	-rm *.cmo *.cmi *.cmx *.o $(OUTPUTS)

.PHONY: all clean
