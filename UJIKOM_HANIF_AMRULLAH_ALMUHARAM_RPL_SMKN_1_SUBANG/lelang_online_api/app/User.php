<?php

namespace App;

use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;

class User extends Authenticatable
{
    use Notifiable;
    protected $guarded = ['id_user'];
    protected $primaryKey = 'id_user';

    protected $hidden = [
        'password', 'remember_token',
    ];

    public function level()
    {
        return $this->belongsTo(Level::class);
    }

    public function masyarakat()
    {
        return $this->hasOne(Masyarakat::class, 'id_user');
    }

    public function petugas()
    {
        return $this->hasOne(Petugas::class, 'id_user');
    }
}
