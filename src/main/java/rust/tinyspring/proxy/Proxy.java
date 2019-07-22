package rust.tinyspring.proxy;

public interface Proxy {

    /**
     *
     * @param proxyChain provide arguments for {@code Proxy} in the chain
     * @return
     * @throws Throwable
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
