# CSEHikingStuff

## A Brief Summary
This project is a project for CSE 248 where we are supposed to make a hiking app that allows for the storage and saving of users and data.
To give as brief overview of the structures used in this project, Both the user data and the hiking history is stored within a treemap.
the Trails Are stored within a hashMap structure set with a maximun size of 5000 trails. The Program itself is able to save both the hashed trails and the treemap for users
into separate .dat files which are later retrieved when the program is running. While it is running, the program saves when the user exits the program. The images for the hiking history are stored in a linked list in order to perform display actions. I used the API variants of each structure for convinience. Lets go in depth now.

##Tree maps
so the users are currently stored in a treemap. the reason for this is because the structure itself is ideal for keeping my users in a sorted order for decent searching. overall, the data structure doesn't really excell in any particular place, such as insertion, deletion, searching. what it does do is that it can act as a general purpose data structure with decent results across that booard. This is good fopr me because there is so much involved in the useres themselves that if i were to specialize a focus on one thing then i would be sacrificing the others, which is not good when you need to have things for soo many other places. I used a treemap for the hiking history for a similar reason, because there is not one main focus that i need for the hiking history. there are different things i need for it that i needed a general structure for it. The main thing that i would say that i needed for both though, would be displaying and searching.

To display it, i had to use an iterator, which is good because it preserves the sorted structure.

##Hash map
I used as hashmap for my trails. the reason i did this is because hashmaps are a more efficient tree than the treemap structure. they have the fastest access to the structure, only having a big O(1). Despite the drawback of when deleting and inserting, it can create nulls, that is less likely to happen because only admins can do that, and they are more likely to add than to delete. Another limitation is that hashmaps are good when you have a set limit to things so you can appropriately factor the load factor. i have my load factor currently at the default for now, because of how big the structure is. but, apart from that, since i know the limit for trails, hashmap is a better alternative than treeMap.

##linked list
the linked list is used to store my picture for the hiking history. I currently have a function that you can scroll through the pictures by clicking a left or right button. the reason linked list is a good structure for this is that this requires me to do a lot of insertion, deletion and re-insertion. Linked lists have one of the fastest insertion and deletion times, however are bad at searching. However, since the users don't need to search for a picture, they can just scroll through the list,
then a linked list is enough for this

###things to implement in further patches
-relative paths
-advanced filter search
-more admins funtionality+ polished code
