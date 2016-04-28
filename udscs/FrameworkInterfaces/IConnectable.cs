using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace FrameworkInterfaces
{
    public interface IConnectable
    {
        void Connect(IPEndPoint ipAddress);
        void Disconnect(IPEndPoint ipAddress);
    }
}
