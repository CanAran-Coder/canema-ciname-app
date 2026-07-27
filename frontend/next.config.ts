import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  images: {
    remotePatterns: [
      {
        protocol: 'https',
        hostname: 'images5.alphacoders.com',
      },

    ],
  },
};

export default nextConfig;
