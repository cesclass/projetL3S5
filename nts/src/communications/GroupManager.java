package communications;

import java.util.ArrayList;
import java.util.List;

import user.Group;

public class GroupManager {
	private List<Group> groupes;
	
    public GroupManager(Group groupe) {
        groupes = new ArrayList<>();
        groupes.add(groupe);
    }
    
    public void addGroup(Group groupe) {
        groupes.add(groupe);
    }
}
