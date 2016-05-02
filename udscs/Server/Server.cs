using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Uds.Communication;
using System.Net;

namespace Uds.Server
{
    public class Server
    {
        ConnectionReceiver ConnectionReceiver;
        Uds.Communication.ConnectionPool ConnectionPool;

        public Server()
        {
            ConnectionPool = new Communication.ConnectionPool();
            ConnectionReceiver = new ConnectionReceiver(new IPEndPoint(Util.GetLocalIPAddress(), 6000), ConnectionPool);
            ConnectionReceiver.OnNewRemoteConnectionReceived += HandleNewRemoteConnection;
        }

        void HandleNewRemoteConnection(Communication.Connection remoteConnection)
        {
            var communicator = new Communicator(remoteConnection);
            communicator.Comunicate();
        }

        public void Start()
        {
            var receiverTask = ConnectionReceiver.Start();
            Task.WaitAll(receiverTask);
        }

        public void Stop()
        {
            ConnectionReceiver.Stop();
        }
    }
}
