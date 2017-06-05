//
// Created by mikhail on 31.05.17.
//

#ifndef UNTITLED_VECTOR3D_H
#define UNTITLED_VECTOR3D_H


#include <cmath>

class Vector3d {
private:
    double x;
    double y;
    double z;
public:
    Vector3d(double x, double y, double z) {
        this->x = x;
        this->y = y;
        this->z = z;
    };

    Vector3d() {};

    void normalize() {
        double norm = Vector3d::norm();
        x /= norm;
        y /= norm;
        z /= norm;
    };

    double norm() {
        return sqrt(x * x + y * y + z * z);
    };

    double dotProduct(Vector3d vector3d) {
        return (vector3d.x * x + vector3d.y * y + vector3d.z * z);
    };

    double getX() const {
        return x;
    };

    double getY() const {
        return y;
    };

    double getZ() const {
        return z;
    };
};


#endif //UNTITLED_VECTOR3D_H
