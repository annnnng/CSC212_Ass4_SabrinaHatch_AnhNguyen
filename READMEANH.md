## Name: Anh Nguyen

## Resources
- TA hours.
- [GeeksforGeeks: ArrayDeque.](https://www.geeksforgeeks.org/arraydeque-in-java/)
- [Nick Howe's Tokenizer.](https://replit.com/@nhowe/TokenizerNotStatic)
- [GeeksforGeeks: Exception.](https://www.geeksforgeeks.org/exceptions-in-java/)

# Reflection

This assignment was rough. I don't know why! It seems like it isn't that hard because we were given the algorithm and we just need to implement it. However, we keep running in issues and trying to debug things even though we have a good idea of what to do and a pretty detailed pseudo-code. 

The hardest part was figuring out how to determine the precedence of the operator. We first try an array and make it return the index of the operator and compare the index. Turns out there is `PriorityQueue`! However, after much reading, we can't figure out how to make the comparator gives `+` and `-` equal level. One fix was to make three arrays, each array contains operator with the same precedence. In addition, we made two arrays that contain left operator and right operator respectively. That was an overkill, so we have the code presently in the program. I think that the array idea is more future-proof: if we want to add more binary operator, we just add them to the correct array. 

Note to self. If using `while(s.hasNext())`, a `s.next()` or a `break` is needed to break the loop! (That took 2 hours out of my life!) `Character` and `String` also took much time from my life. They don't share the same methods, you can't compare a `Character` to a `String` and many other things. 

I am trying to implement the unary minus sign but how do I make the program distinguish between a minus and a unary minus sign, they are both `-`. I know on my calculator, they are different symbol. Also what if there are multiple constant or function? I was thinking we can make a program to look up the word (`pi` or `sin`) and find the corresponding value (`3.14` or performing trig function). A map...? Could we just put each step in the algorithm in a map, as in the key would be say an operator and the value would be a specific code chunks. 

And we are still having trouble with `throw`. I think it is because we are not sure what class of Exception to throw or if we need to make our own Exception. 

We should have just use Prof. Howe's Tokenizer to read in each token and store them in queue for both postfix and infix expression program and then do the calculation/conversion. What we did was to directly change the Tokenizer code to accommodate postfix and infix seperately. I would love to see what is like a good codes for implementing the calculator. 
