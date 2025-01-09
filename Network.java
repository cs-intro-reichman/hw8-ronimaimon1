/** Represents a social network. The network has users, who follow other uesrs.
 *  Each user is an instance of the User class. */
public class Network {

    // Fields
    private User[] users;  // the users in this network (an array of User objects)
    private int userCount; // actual number of users in this network

    /** Creates a network with a given maximum number of users. */
    public Network(int maxUserCount) {
        this.users = new User[maxUserCount];
        this.userCount = 0;
    }

    /** Creates a network  with some users. The only purpose of this constructor is 
     *  to allow testing the toString and getUser methods, before implementing other methods. */
    public Network(int maxUserCount, boolean gettingStarted) {
        this(maxUserCount);
        users[0] = new User("Foo");
        users[1] = new User("Bar");
        users[2] = new User("Baz");
        userCount = 3;
    }

    public int getUserCount() {
        return this.userCount;
    }
    /** Finds in this network, and returns, the user that has the given name.
     *  If there is no such user, returns null.
     *  Notice that the method receives a String, and returns a User object. */
    public User getUser(String name) {

        // check if the given name is not null
        if (name==null)
            return null;
            
        // going throw the users list in this network
        for (int i=0; i<userCount; i++){
            // comparring the cuurent user name to the given name
            if (((users[i].getName()).toLowerCase()).equals(name.toLowerCase())){
                return users[i];
            }
        }
        // when there is no such user with the given name in this network
        return null;
    }

    /** Adds a new user with the given name to this network.
    *  If ths network is full, does nothing and returns false;
    *  If the given name is already a user in this network, does nothing and returns false;
    *  Otherwise, creates a new user with the given name, adds the user to this network, and returns true. */
    public boolean addUser(String name) {

        // check if this network is full or if the given name is already a user in this network
        if ((users.length == userCount) || (this.getUser(name)!=null))
            return false;

        // otherwise, create a new user with the given name and add it to the users list of this network
        users[userCount] = new User(name);

        // update the userCount and return true
        userCount++;
        return true;
    }

    /** Makes the user with name1 follow the user with name2. If successful, returns true.
     *  If any of the two names is not a user in this network,
     *  or if the "follows" addition failed for some reason, returns false. */
    public boolean addFollowee(String name1, String name2) {

        // check if the given names are users in this network
        if ((this.getUser(name1)==null) || (this.getUser(name2)==null))
            return false;

        // trying to add the user with name2 to the follow list of the user with name1
        // return the result
        return (this.getUser(name1)).addFollowee(name2);
    }
    
    /** For the user with the given name, recommends another user to follow. The recommended user is
     *  the user that has the maximal mutual number of followees as the user with the given name. */
    public String recommendWhoToFollow(String name) {
        // assume that the user with the given name is a user in this network

        String res = "";
        int maxMutual = -1;
        // going throw this network users list
        for (int i=0; i<userCount; i++){
            // when the cuurent user is the same as the user with the given name
            if (this.getUser(name)==users[i])
                continue;
            // search for the user with the maximal number of mutual followees
            if (users[i].countMutual(this.getUser(name)) > maxMutual){
                maxMutual = users[i].countMutual(this.getUser(name));
                res = users[i].getName();
            }
        }
        return res;
    }

    /** Computes and returns the name of the most popular user in this network: 
     *  The user who appears the most in the follow lists of all the users. */
    public String mostPopularUser() {
        String mostPopularUser = null;
        int max = 0;
        for (int i=0; i<userCount; i++){
            if (this.followeeCount(users[i].getName()) > max){
                max = this.followeeCount(users[i].getName());
                mostPopularUser = users[i].getName();
            }
        }
        return mostPopularUser;
    }

    /** Returns the number of times that the given name appears in the follows lists of all
     *  the users in this network. Note: A name can appear 0 or 1 times in each list. */
    private int followeeCount(String name) {
        int followeeCount = 0;

        // going throw all the users in this network
        for (int i=0; i<userCount; i++){
            // check if the cuurent user follow the user with the given name and increase the followee count by 1
            if (users[i].follows(name))
                followeeCount++;
        }
        return followeeCount;
    }

    // Returns a textual description of all the users in this network, and who they follow.
    public String toString() {
       String str = "Network:";
       for (int i=0; i<userCount; i++){
        str += "\n" + users[i] + "";
       }
       return str;
    }
}
