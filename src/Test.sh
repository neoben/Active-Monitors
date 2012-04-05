#!/bin/bash
# compile everything

echo "Compilazione Server.java"
javac Server.java

echo "Compilazione terminata"
echo "Per eseguire il test digitare: java Server 5"

echo "*******************"

echo "Compilazione Server con priorità ServerP.java"
javac ServerP.java

echo "Compilazione terminata"
echo "Per eseguire il test digitare: java ServerP 5"
