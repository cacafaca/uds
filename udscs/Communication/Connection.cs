using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace Uds.Communication
{
    public class Connection
    {
        Socket Socket;
        public Connection()
        {
            Socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
        }

        public Connection(Socket socket)
        {
            if (socket == null)
                throw new ArgumentNullException("Socket can not be null.");
            Socket = socket;
        }

        public void Listen(IPEndPoint localEndPoint)
        {
            Socket.Bind(localEndPoint);
            Socket.Listen(100);
        }

        public void Connect(IPEndPoint remoteEndPoint)
        {
            throw new NotImplementedException();
        }

        public byte[] Receive()
        {
            byte[] receiveBuffer = new byte[_ReadBufferSize];
            byte[] resultBuffer = new byte[0];
            int receivedCount = 0;
            while ((receivedCount = Socket.Receive(receiveBuffer)) > 0)
            {
                int oldResultBufferLength = resultBuffer.Length;
                Array.Resize(ref resultBuffer, resultBuffer.Length + receivedCount);
                Array.Copy(receiveBuffer, 0, resultBuffer, oldResultBufferLength, receivedCount);
                if (receivedCount < _ReadBufferSize)
                    break;
            }
            return resultBuffer;
        }

        private int _ReadBufferSize = 1024;
        public int ReadBufferSize { get { return _ReadBufferSize; } }
      

        public Connection Accept()
        {
            return new Connection(Socket.Accept());
        }

        /// <summary>
        /// Is it active or not. Something like that.
        /// </summary>
        /// <returns></returns>
        public int State()
        {
            return int.MinValue;
        }
    }
}
