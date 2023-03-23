package cat.udl.hyperion.appmobils.kingdomcollector.views;

public interface LastLoginCallback {
    void onSuccess(String lastLogin);
    void onFailure(String errorMessage);
}
