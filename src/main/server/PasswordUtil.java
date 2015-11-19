package main.server;
public final class PasswordUtil
{
    private PasswordUtil()
    {
    }
    public static void main(String a[]) throws Exception
    {
        PasswordService ps=new PasswordService();
        System.out.println(ps.encrypt("srinivaa"));
    }
}
 