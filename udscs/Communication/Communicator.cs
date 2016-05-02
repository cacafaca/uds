using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace Uds.Communication
{
    public class Communicator
    {
        public Communicator(Connection connection)
        {
            Connection = connection;
        }
        Connection Connection;

        public void Comunicate()
        {
            System.Diagnostics.Debug.WriteLine("Start communicating.");
            Task.Factory.StartNew(() => Receive());
        }

        private void Receive()
        {
            while (true)
            {
                var receivedData = Connection.Receive();
                Message message = Util.DeserializeMessage(receivedData);
            }
        }

    }
}
