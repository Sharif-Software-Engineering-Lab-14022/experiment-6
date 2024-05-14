public interface PackageState {
    String getStatus();
    void updateState(PackageContext context);
}
