package cat.udl.hyperion.appmobils.kingdomcollector.other.auth;

public interface LastLoginCallback {
    void onSuccess(String lastLogin);
    void onFailure(String errorMessage);
}
