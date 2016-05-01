using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace Uds.Server
{
    /// <summary>
    /// Receives new connections and add them in the connection pool.
    /// </summary>
    class ConnectionReceiver
    {
        public ConnectionReceiver(IPEndPoint localIPEndPoint, Communication.ConnectionPool connectionPool)
        {
            if (localIPEndPoint == null)
                throw new ArgumentNullException("End point can not be null.");
            LocalIPEndPoint = localIPEndPoint;

            if (connectionPool == null)
                throw new ArgumentNullException("Connection pool can not be null.");
            ConnectionPool = connectionPool;
        }

        IPEndPoint LocalIPEndPoint;
        Communication.ConnectionPool ConnectionPool;
        Communication.Connection ConnectionListener;

        public Task Start()
        {
            ConnectionListener = new Communication.Connection();
            ConnectionListener.Listen(LocalIPEndPoint);
            return Task.Factory.StartNew(() => StartToReceiveConnections());
        }

        private void StartToReceiveConnections()
        {
            while (true)
            {
                var newRemoteConnection = ConnectionListener.Accept();
                ConnectionPool.Add(newRemoteConnection);
                if (OnNewRemoteConnectionReceived != null)
                    OnNewRemoteConnectionReceived(newRemoteConnection);
            }
        }

        public void Stop()
        {

        }

        public delegate void NewRemoteConnectionReceived(Communication.Connection remoteConnection);
        public event NewRemoteConnectionReceived OnNewRemoteConnectionReceived;
    }
}
