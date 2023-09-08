#!/bin/bash

rm tests.out
rm out
#javac *.java
rc=$?
total=0
if javac *.java; then
  tests=( "view"
          "find 4715004908"  
          "find 9999999999"
          "sort number"
          "sort balance"
          "withdraw 5792112384 9000"
          "withdraw 5792112384 1000"
          "deposit 6754917864 1000"
          "interest 4715004908"
          "interest 9512559591"
          "profit 1378190104"
          "profit 7070611070"
        )

  for test in "${tests[@]}"; do
    total=$((total + 1))
    echo -e "\nTest $total with args: $test"
    java BankManager $test | tee -a tests.out
  done
  if java AutoTest tests.reference tests.out; then
      exit 0
  else
      exit 1
  fi
else
  exit 1
fi


