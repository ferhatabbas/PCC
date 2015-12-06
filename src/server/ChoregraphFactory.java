package server;

/**
 * Created by Sylvain on 2015-12-05.
 */
public class ChoregraphFactory implements RequestHandlerFactory {
    @Override
    public RequestHandler newRequestHandler() {
        return new Choregraph();
    }
}
