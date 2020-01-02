package communications;

import java.util.ArrayList;
import java.util.List;

import user.Group;

public class GroupManager {
	private List<Group> groupes;
	
    public GroupManager() {
        groupes = new ArrayList<>();
    }
    
    public void addGroup(Group groupe) {
        groupes.add(groupe);
    }
}
