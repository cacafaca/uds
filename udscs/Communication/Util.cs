using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Xml.Serialization;
using System.IO;
using System.Collections.Generic;

namespace Uds.Communication
{
    public static class Util
    {
        public static IPAddress GetLocalIPAddress()
        {
            return Dns.GetHostEntry(Dns.GetHostName()).AddressList.Where(adr => adr.AddressFamily == System.Net.Sockets.AddressFamily.InterNetwork).First();
        }
    }
}
