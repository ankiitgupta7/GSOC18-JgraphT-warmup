# GSoC18-JGraphT
This contains the code and related files regarding the project for GSOC18 warmup of the Java library Org JgraphT...

*For compiling, make sure that you add the jars available in directory jar_files.

*For running, use the input graph from the dot_file dierctory and find the leaves to be searched from their respective png files in the directory png_files. 

*Note that graph list1.dot is cyclic so it will not show the LCA as Class NaiveLcaFinder works only for DAGs.

*Use the test.dot graph for checking the genuinity of the code as it is proper DAG and hence the code works perfectly for this. Get the leaves names from test.png file of png_files.
