package service;

import exceptions.MemberNotFoundException;
import java.util.ArrayList;
import model.Member;

// Stores all team members and does the member related work
public class MemberService {
    private ArrayList<Member> members = new ArrayList<>();

    // Adds a member. Returns false if that ID is already used.
    public boolean addMember(String id, String name) {
        if (findMemberOrNull(id) != null) {
            return false;
        }
        members.add(new Member(id, name));
        return true;
    }

    // Prints every member
    public void viewAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No team members have been registered.");
            return;
        }

        System.out.println("\n--- Team Members ---");
        for (Member member : members) {
            member.displayDetails();
            System.out.println("--------------------");
        }
    }

    // Finds a member by ID. Throws our exception if there is no such member.
    public Member findMember(String id) throws MemberNotFoundException {
        Member member = findMemberOrNull(id);
        if (member == null) {
            throw new MemberNotFoundException("Member with ID '" + id + "' was not found.");
        }
        return member;
    }

    // Helper: loops through the list and returns the member, or null if not found
    private Member findMemberOrNull(String id) {
        for (Member member : members) {
            if (member.getId().equalsIgnoreCase(id)) { // ignore capital / small letters
                return member;
            }
        }
        return null;
    }
}
