OCAMLOPT=ocamlopt.opt
OCAMLC=ocamlc.opt
CMIOPTIONS=
CMOOPTIONS=
CMXOPTIONS=

INTERFACES=$(wildcard *.mli)
INTERFACE_OBJECTS=$(INTERFACES:.mli=.cmi)
SOURCES=$(wildcard *.ml)
OBJECTS=$(SOURCES:.ml=.cmx)
OUTPUTS=SortTest

all: $(INTERFACE_OBJECTS) $(OBJECTS) SortTest

SortTest: BubbleSort.cmx BSTree.cmx SortTest.cmx
	$(OCAMLOPT) -o $@ $+

%.cmx: %.ml
	$(OCAMLOPT) -o $@ -c $(CMXOPTIONS) $<

%.cmi: %.mli
	$(OCAMLOPT) -o $@ -c $(CMIOPTIONS) $<

%.cmo: %.ml
	$(OCAMLC) -o $@ -c $(CMOOPTIONS) $<
clean:
	-$(RM) *.cmo *.cmi *.cmx *.o $(OUTPUTS)

.PHONY: all clean 
