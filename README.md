## Test performance between MySQL and MongoDB
    MySQL version 5.6.24, for osx10.10 (x86_64)
    MongoDB version 3.0.3 for osx10.10 (x86_64)

#Test case:
  1. Insert 100000 records.
  2. query record base on condition
  
Condition: 
table structure
user_user
		from_id INT
		to_id INT
		relation STRING
		(from_id,to_id,relation) UNIQUE
both MySQL and MongoDB have been created index for from_id
TABLE AND DATA ARE CREATE AND INSERT FOR EVERYTIME RUN THE PROGRAM





          MySQL insert 100000 record  MongoDB insert 100000 record     MySQL query set of records    MongoDB query set of records
  	Round 1           18342 ms                      9257 ms                       1 ms                            2 ms
  	Round 2           17790 ms                      8874 ms                       1 ms                            2 ms
  	Round 3           17649 ms                      9613 ms                       0 ms approx*                    2 ms
  	Round 4           17226 ms                      9023 ms                       1 ms                            2 ms

approx* this is result for System.currentTimeMillis() Subtraction, after execution minus before execution
