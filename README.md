# ALE Compiler

## Introduction
The *Revisitor* pattern is a language implementation pattern that enables independent extensibility of the syntax and semantics of metamodel-based DSLs, with incremental compilation and without anticipation.
It is inspired by the [Object Algebra](https://dl.acm.org/citation.cfm?id=2367167) design pattern and adapted to the specificities of metamodeling.

On top of the *Revisitor* pattern, we introduce *ALE*, a high-level language for semantics specification of metamodels that compiles to *Revisitors* to support separate compilation of language modules.

*ALE* is tightly integrated with the [Eclipse Modeling Framework](https://www.eclipse.org/modeling/emf/) (EMF) and relies on the Ecore meta-language for the definition of the abstract syntax of DSLs. Operational semantics is defined with *ALE* using an open-class-like mechanism. *ALE* is bundled a set of Eclipse plug-ins.

## Installation

1. Download an Eclipse IDE for Java and DSL Developers (Neon.3) for your platform: http://www.eclipse.org/downloads/packages/eclipse-ide-java-and-dsl-developers/neon3
1. Install the ale plugins using the update site: http://gemoc.org/ale/revisitors/updatesite/. Follow the [procedure](http://help.eclipse.org/oxygen/index.jsp?topic=/org.eclipse.platform.doc.user/tasks/tasks-127.htm) and select every plugin available.
1. Restart your Eclipse environment
1. You're all set!

## Usage

The ALE plug-ins provide two main operations to the user:

* On an Ecore (*.ecore) metamodel: `Right click -> ALE -> Generate Revisitor interface` generates the corresponding *Revisitor interface* in the *src* directory of the current project
* On an ALE (*.ale) file: `Right click -> ALE -> Generate Revisitor implementation` generates the corresponding *Revisitor* implementation in the *src* directory of the current project
* (note that *Revisitor* implementations depend on *Revisitor* interfaces and will not compile otherwise)

## Building the update site

The ALE update site can be rebuilt from scratch using the [ale.p2updatesite project](./ale.p2updatesite). 

After importing the projects into eclipse, open the [ale.p2updatesite/site.xml](./ale.p2updatesite/site.xml) file and click "Build All". This will publish the update site content within the ale.p2updatesite directory.
