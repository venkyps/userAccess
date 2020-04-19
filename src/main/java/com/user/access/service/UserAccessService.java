package com.user.access.service;

public interface UserAccessService {

    void persistUserAccess(String empID) throws Exception;

    void persistAllUserAccess() throws Exception;
}
