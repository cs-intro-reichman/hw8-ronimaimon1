/** Represents a user in a social network. A user is characterized by a name,
 *  a list of user names that s/he follows, and the list's size. */
 public class User {

    // Maximum number of users that a user can follow
    static int maxfCount = 10;

    private String name;       // name of this user
    private String[] follows;  // array of user names that this user follows
    private int fCount;        // actual number of followees (must be <= maxfCount)

    /** Creates a user with an empty list of followees. */
    public User(String name) {
        this.name = name;
        follows = new String[maxfCount]; // fixed-size array for storing followees
        fCount = 0;                      // initial number of followees
    }

    /** Creates a user with some followees. The only purpose of this constructor is 
     *  to allow testing the toString and follows methods, before implementing other methods. */
    public User(String name, boolean gettingStarted) {
        this(name);
        follows[0] = "Foo";
        follows[1] = "Bar";
        follows[2] = "Baz";
        fCount = 3;
    }

    /** Returns the name of this user. */
    public String getName() {
        return name;
    }

    /** Returns the follows array. */
    public String[] getfFollows() {
        return follows;
    }

    /** Returns the number of users that this user follows. */
    public int getfCount() {
        return fCount;
    }

    /** If this user follows the given name, returns true; otherwise returns false. */
    public boolean follows(String name) {
        for (int i=0; i<fCount; i++){
            if ((follows[i].toLowerCase()).equals((name.toLowerCase())))
                return true;
        }
        return false;
    }
    /** Makes this user follow the given name. If successful, returns true. 
     *  If this user already follows the given name, or if the follows list is full, does nothing and returns false; */
    public boolean addFollowee(String name) {

        // if the follows list if full or if this user already follows the given name or if this user try to follow itself
        if ((follows.length == fCount) || (this.follows(name)) || (name.toLowerCase()).equals(this.name.toLowerCase()))
            return false;
        // else, adding the given name to the follows list and increase fCount by 1
        follows[fCount] = name;
        fCount++;
        return true;
    }

    /** Removes the given name from the follows list of this user. If successful, returns true.
     *  If the name is not in the list, does nothing and returns false. */
    public boolean removeFollowee(String name) {
         // find the index of the given name the follows array
         for (int i=0; i<fCount; i++){
            if (follows[i].equals(name)){
                // removing the given name from the follows list by move all the array elements below this name one position “up”
                for (int j=i; j<fCount-1; j++){
                    follows[j] = follows[j+1];
                }
                follows[fCount-1] = null;
                fCount--;
                return true;
            }
        }
        // if this doesnt follow the given name
        return false;
    }

    /** Counts the number of users that both this user and the other user follow.
    /*  Notice: This is the size of the intersection of the two follows lists. */
    public int countMutual(User other) {
        int mutual = 0;
        // going throw this follows names
        for (int i=0; i<this.fCount; i++){
            // check if other follows the current name of this follows
            if (other.follows(this.follows[i]))
                mutual++;
        }
        return mutual;
    }

    /** Checks is this user is a friend of the other user.
     *  (if two users follow each other, they are said to be "friends.") */
    public boolean isFriendOf(User other) {
        if (this.follows(other.name) && other.follows(this.name))
                return true;
        return false;
    }
    /** Returns this user's name, and the names that s/he follows. */
    public String toString() {
        String ans = name + " -> ";
        for (int i = 0; i < fCount; i++) {
            ans = ans + follows[i] + " ";
        }
        return ans;
    }
}
