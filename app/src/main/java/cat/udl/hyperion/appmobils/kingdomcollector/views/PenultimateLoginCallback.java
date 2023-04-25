package cat.udl.hyperion.appmobils.kingdomcollector.views;

public interface PenultimateLoginCallback {

    void onFailure(String usuario_no_autenticado);

    void onSuccess(String secondLastLogin);
}