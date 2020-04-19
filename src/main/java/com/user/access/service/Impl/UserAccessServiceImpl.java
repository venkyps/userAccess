package com.user.access.service.Impl;

import com.user.access.entity.TeamHierarchy;
import com.user.access.entity.User;
import com.user.access.entity.UserAccess;
import com.user.access.repo.TeamHierarchyRepository;
import com.user.access.repo.UserAccessRepository;
import com.user.access.repo.UserRepository;
import com.user.access.service.UserAccessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserAccessServiceImpl implements UserAccessService {

    private static final Logger log = LoggerFactory.getLogger(UserAccessServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamHierarchyRepository teamHierarchyRepository;

    @Autowired
    private UserAccessRepository userAccessRepository;

    @Override
    public void persistUserAccess(String empID) throws Exception {
        try{
            Optional<TeamHierarchy> teamHierarchy=teamHierarchyRepository.findById(empID);
            if(teamHierarchy.isPresent()){
                List<User> userList=userRepository.getUserList(empID,teamHierarchy.get().getManagerID());
                Map<String,User> userMap = userList.stream().collect(
                        Collectors.toMap(User::getEmpID, user->user));
                userAccessRepository.save(setUserAccess(empID,teamHierarchy.get().getManagerID(),userMap));
            } else{
                Optional<User> user=userRepository.findById(empID);
                UserAccess userAccess = setUserAccessManager(user.get());
                userAccessRepository.save(userAccess);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private UserAccess setUserAccessManager(User user) {
        UserAccess userAccess = new UserAccess();
        userAccess.setAccessKey(user.getAccessKey());
        userAccess.setEmpID(user.getEmpID());
        userAccess.setCountry(user.getCountry());
        userAccess.setSubUserAccessKey(user.getAccessKey());
        userAccess.setSubuser(user.getEmpID());
        userAccess.setSubUsercountry(user.getCountry());
        return userAccess;
    }

    private UserAccess setUserAccess(String empID,String managerID, Map<String,User> userMap) {
        UserAccess userAccess = new UserAccess();
        if(Objects.nonNull(userMap)){
            userAccess.setAccessKey(userMap.get(managerID).getAccessKey());
            userAccess.setEmpID(userMap.get(managerID).getEmpID());
            userAccess.setCountry(userMap.get(managerID).getCountry());
            userAccess.setSubUserAccessKey(userMap.get(empID).getAccessKey());
            userAccess.setSubuser(userMap.get(empID).getEmpID());
            userAccess.setSubUsercountry(userMap.get(empID).getCountry());
        }
        return userAccess;
    }

    @Override
    public void persistAllUserAccess() throws Exception {

        List<UserAccess> userAccessList = new ArrayList<>();
        List<TeamHierarchy> teamHierarchyList = (List<TeamHierarchy>) teamHierarchyRepository.findAll();
        List<User> userList= (List<User>) userRepository.findAll();

        Map<String,User> userMap = userList.stream().collect(Collectors.toMap(User::getEmpID, user->user));
        List<String> empList= teamHierarchyList.stream().map(TeamHierarchy::getEmpID).collect(Collectors.toList());
        List<String> managerList= teamHierarchyList.stream().map(TeamHierarchy::getManagerID).collect(Collectors.toList());
        managerList.removeAll(empList);

        String manager= managerList.stream().distinct().findFirst().get();
        userAccessList.addAll(setManagerAccess(manager,userMap,empList));
        setTeamUserAccess(userAccessList, teamHierarchyList, userMap, manager);
        userAccessRepository.saveAll(userAccessList);
    }

    private void setTeamUserAccess(List<UserAccess> userAccessList, List<TeamHierarchy> teamHierarchyList, Map<String, User> userMap, String manager) {
        Map<String,List<String>> teamMap = new HashMap<>();
        teamHierarchyList.stream().forEach(teamHierarchy -> {
            List<String> tempEmpList= new ArrayList<>();
           if(teamMap.containsKey(teamHierarchy.getManagerID())){
               List empIDList= teamMap.get(teamHierarchy.getManagerID());
               empIDList.add(teamHierarchy.getEmpID());
               teamMap.put(teamHierarchy.getManagerID(),empIDList);
           }else{
               tempEmpList.add(teamHierarchy.getEmpID());
               teamMap.put(teamHierarchy.getManagerID(),tempEmpList);
           }
       });
        teamMap.remove(manager);
        teamMap.forEach((k,v)->{
            v.add(k);
            userAccessList.addAll(setTeamUserAccess(k,userMap,v));
        });
    }


    private List<UserAccess> setManagerAccess(String manager,Map<String,User> userMap,List<String> empList){
        List <UserAccess> userAccessList = new ArrayList<>();
        UserAccess userAccessManager = new UserAccess();
        userAccessManager.setCountry(userMap.get(manager).getCountry());
        userAccessManager.setAccessKey(userMap.get(manager).getAccessKey());
        userAccessManager.setEmpID(userMap.get(manager).getEmpID());
        userAccessManager.setSubuser(userMap.get(manager).getEmpID());
        userAccessManager.setSubUserAccessKey(userMap.get(manager).getAccessKey());
        userAccessManager.setSubUsercountry(userMap.get(manager).getCountry());
        userAccessList.add(userAccessManager);

        empList.forEach(key-> {
            UserAccess userAccess = new UserAccess();
            userAccess.setCountry(userMap.get(manager).getCountry());
            userAccess.setAccessKey(userMap.get(manager).getAccessKey());
            userAccess.setEmpID(userMap.get(manager).getEmpID());
            userAccess.setSubuser(userMap.get(key).getEmpID());
            userAccess.setSubUserAccessKey(userMap.get(key).getAccessKey());
            userAccess.setSubUsercountry(userMap.get(key).getCountry());
            userAccessList.add(userAccess);
        });
        return userAccessList;
    }

    private List <UserAccess> setTeamUserAccess(String manager,Map<String,User> userMap,List<String> empList){
       List <UserAccess> userAccessList = new ArrayList<>();

        empList.forEach(key-> {
            empList.forEach(k-> {
            UserAccess userAccess = new UserAccess();
            userAccess.setCountry(userMap.get(key).getCountry());
            userAccess.setAccessKey(userMap.get(key).getAccessKey());
            userAccess.setEmpID(userMap.get(key).getEmpID());
            userAccess.setSubuser(userMap.get(k).getEmpID());
            userAccess.setSubUserAccessKey(userMap.get(k).getAccessKey());
            userAccess.setSubUsercountry(userMap.get(k).getCountry());
            userAccessList.add(userAccess);
            });
        });
        return userAccessList;
    }
}
