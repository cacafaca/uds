using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace Communication
{
    public class Connection
    {
        Socket Socket;
        public Connection()
        {
            Socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
        }
             
        public void Listen(IPEndPoint localEndPoint)
        {
            Socket.Bind(localEndPoint);
            Socket.Listen(100);
        }

        public void Connect(IPEndPoint remoteEndPoint)
        {
            Connect(remoteEndPoint);
        }

        public byte[] Read()
        {

            byte[] buf = new byte[256];
            Socket.Receive(buf);
            return buf;
        }

        public Socket Accept()
        {
            return Socket.Accept();
        }
    }
}
