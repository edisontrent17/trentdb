import http.server
import socketserver
import socket  # To get the hostname and IP

# --- Configuration ---
# Host: '0.0.0.0' means the server will listen on all available network interfaces.
# This is crucial for other devices on the network to reach it.
HOST = "0.0.0.0"
PORT = 8000  # You can change this port if 8000 is already in use


# --- Get local IP address to display (optional, for convenience) ---
def get_local_ip():
    try:
        # Create a dummy socket to connect to an external address
        s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        s.settimeout(0)
        s.connect(('10.254.254.254', 1))  # Doesn't have to be reachable
        IP = s.getsockname()[0]
    except Exception:
        IP = '127.0.0.1'  # Fallback
    finally:
        s.close()
    return IP


# --- Define the Handler ---
# This will serve files from the directory where you run the script.
# For more advanced features, you would create a custom handler.
Handler = http.server.SimpleHTTPRequestHandler

# --- Start the Server ---
if __name__ == "__main__":
    local_ip = get_local_ip()
    hostname = socket.gethostname()

    # Create the server
    with socketserver.TCPServer((HOST, PORT), Handler) as httpd:
        print(f"Serving HTTP on {HOST} port {PORT} (PID: {os.getpid() if 'os' in globals() else 'N/A'})...")
        print(f"You can access this server from other devices on the same Wi-Fi network using:")
        print(f"  http://{local_ip}:{PORT}")
        print(
            f"  (Or, if your computer's hostname '{hostname}' is resolvable on your network: http://{hostname}:{PORT})")
        print("\nTo see files, make sure you run this script in the directory")
        print("whose contents you want to serve, or create an 'index.html' file.")
        print("\nPress Ctrl+C to stop the server.")
        try:
            httpd.serve_forever()
        except KeyboardInterrupt:
            print("\nServer stopped.")
            httpd.shutdown()
