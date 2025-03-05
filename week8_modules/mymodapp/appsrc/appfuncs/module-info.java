
module appfuncs{
    // qualified export available to appstart only. This can be a space sperated list as well.
    exports appfuncs.simplefuncs to appstart;
    // Unqualified export available to all modules
//    exports appfuncs.simplefuncs;
}