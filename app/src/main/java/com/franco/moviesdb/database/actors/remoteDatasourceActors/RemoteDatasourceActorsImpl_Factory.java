package com.franco.moviesdb.database.actors.remoteDatasourceActors;

import com.franco.moviesdb.network.api.ApiService;

import dagger.internal.Factory;

import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
        value = "dagger.internal.codegen.ComponentProcessor",
        comments = "https://dagger.dev"
)
@SuppressWarnings({
        "unchecked",
        "rawtypes"
})
public final class RemoteDatasourceActorsImpl_Factory implements Factory<RemoteDatasourceActorsImpl> {
  private final Provider<ApiService> serviceProvider;

  public RemoteDatasourceActorsImpl_Factory(Provider<ApiService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  public static RemoteDatasourceActorsImpl_Factory create(Provider<ApiService> serviceProvider) {
    return new RemoteDatasourceActorsImpl_Factory(serviceProvider);
  }

  public static RemoteDatasourceActorsImpl newInstance(ApiService service) {
    return new RemoteDatasourceActorsImpl(service);
  }

  @Override
  public RemoteDatasourceActorsImpl get() {
    return newInstance(serviceProvider.get());
  }
}
