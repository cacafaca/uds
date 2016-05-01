using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

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
            var receivedData = Connection.Receive();
        }
    }
}
