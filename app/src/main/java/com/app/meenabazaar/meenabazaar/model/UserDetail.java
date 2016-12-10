package com.app.meenabazaar.meenabazaar.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by lovishbajaj on 12/11/16.
 */

public class UserDetail implements KvmSerializable{

    public String EmailId;
    public String UserId;
    public String UserName;
    public String UserRoleId;
    public String UserRoleName;
    public String BranchId;
    public String BranchName;


    public UserDetail(){}

    public UserDetail(String emailId,String userId,String userName,String userRoleId,String userRoleName, String branchId,String branchName){
        this.EmailId=emailId;
        this.UserId=userId;
        this.UserName=userName;
        this.UserRoleId=userRoleId;
        this.BranchId=branchId;
        this.BranchName=branchName;
        this.UserRoleName=userRoleName;
    }
    public Object getProperty(int arg0){
        switch (arg0){
            case 0:
                return EmailId;
            case 1:
                return UserId;
            case 2:
                return UserName;
            case 3:
                return UserRoleId;
            case 4:
                return UserRoleName;
            case 5:
                return BranchId;
            case 6:
                return BranchName;
        }
        return null;
    }
    public int getPropertyCount(){
        return 7;
    }
    @SuppressWarnings("RawTypes")
    public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info){
        switch (index){
            case 0:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "EmailId";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "UserId";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "UserName";
                break;
            case 3:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "UserRoleId";
                break;
            case 4:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "UserRoleName";
                break;
            case 5:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "BranchId";
                break;
            case 6:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "BranchName";
                break;
        }
    }
    public void setProperty(int index,Object value){
        switch (index)
        {
            case 0:
                EmailId = value.toString();
                break;
            case 1:
                UserId = value.toString();
                break;
            case 2:
                UserName = value.toString();
                break;
            case 3:
                UserRoleId = value.toString();
                break;
            case 4:
                UserRoleName = value.toString();
                break;
            case 5:
                BranchId = value.toString();
                break;
            case 6:
                BranchName = value.toString();
                break;

        }
    }
}
