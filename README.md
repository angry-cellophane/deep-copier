# Deep Java Object Copier
This is a java library that allows to copy any object with no constraint put on it.
The library based on the Reflection API.
It could managed to copy a graph of object saving links to the corresponding object.

Usage: 
just call CopyUtil.deepCopy(object) to get a full copy of the object or CopyUtil.shallowCopy(object) to get a shallow copy 
